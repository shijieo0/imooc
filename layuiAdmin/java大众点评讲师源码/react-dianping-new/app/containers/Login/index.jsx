import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'
import { hashHistory } from 'react-router'
import { postPhone } from '../../fetch/user/login.js'
import { postLogin } from '../../fetch/user/login.js'
import { md5} from '../../util/md5.js'

import * as userInfoActionsFromOtherFile from '../../actions/userinfo' 

import Header from '../../components/Header'
import LoginComponent from '../../components/Login'

class Login extends React.Component {
    constructor(props, context) {
        super(props, context);
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this);
        this.state = {
            checking: true
        }
    }
    render() {
        return (
            <div>
                <Header title="登录"/>
                {
                    // 等待验证之后，再显示登录信息
                    this.state.checking
                    ? <div>{/* 等待中 */}</div>
                    : <LoginComponent
                     sendSmsHandle={this.sendSmsHandle.bind(this)}
                     loginHandle={this.loginHandle.bind(this)}
                     />
                }
            </div>
        )
    }
    componentDidMount() {
        // 判断是否已经登录
        this.doCheck()
    }
    doCheck() {
        const userinfo = this.props.userinfo
        if (userinfo.username) {
            // 已经登录，则跳转到用户主页
            this.goUserPage();
        } else {
            // 未登录，则验证结束
            this.setState({
                checking: false
            })
        }
    }
    saveSmsInfo(phone,code) {
        // 保存短信验证码
        const actions = this.props.userInfoActions
        let userinfo = this.props.userinfo
        userinfo.code = code
        userinfo.username = phone
        actions.update(userinfo)
    }
    saveUserInfo(username,token) {
        // 保存用户名
        const actions = this.props.userInfoActions
        let userinfo = this.props.userinfo

        if(username === userinfo.username)
        {
            userinfo.username = username
            userinfo.code = null
            userinfo.token = token
            actions.update(userinfo)

            const params = this.props.params
            const router = params.router
            if (router) {
            // 跳转到指定的页面
                hashHistory.push(router)
            } else {
            // 跳转到用户主页
                this.goUserPage()
            }
        }
        else
        {
            alert("not right")
        }


    }
    // 处理登录之后的事情
    loginHandle(username,code) {

        const result = postLogin(username, md5(code))

        result.then(res => {
            return res.json()
        }).then(json => {
            const data = json
            console.log("login r:" + data.msg)
            console.log("login r:" + data.errno)
            if(data.errno === 0)
            {
                //登录成功
                console.log(data.token)
                this.saveUserInfo(username,data.token)
            }
            else
            {
                //登录失败
                alert(data.msg)
            }

        }).catch(ex => {
            if (__DEV__) {
                console.error('数据出错, ', ex.message)
            }
        })

    }
    // 处理发送短信的事情
    sendSmsHandle(phone) {

        const result = postPhone(phone)
        result.then(res => {
            return res.json()
        }).then(json => {
            const data = json
            console.log("sms r:" + data.msg)
            console.log("sms r:" + data.errno)
            console.log("sms r:" + data.code)

            if(data.errno === 0)
            {
                this.saveSmsInfo(phone,data.code);
            }
            else
            {
                //未收到验证码
                alert(data.msg)
            }

        }).catch(ex => {
            if (__DEV__) {
                console.error('数据出错, ', ex.message)
            }
        })


    }
    goUserPage() {
        hashHistory.push('/User')
    }
}

// -------------------redux react 绑定--------------------

function mapStateToProps(state) {
    return {
        userinfo: state.userinfo
    }
}

function mapDispatchToProps(dispatch) {
    return {
        userInfoActions: bindActionCreators(userInfoActionsFromOtherFile, dispatch)
    }
}
export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Login)