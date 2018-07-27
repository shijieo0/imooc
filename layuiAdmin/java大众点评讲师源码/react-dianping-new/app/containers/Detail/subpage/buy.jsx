import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'
import { hashHistory } from 'react-router'
import {postBuy} from '../../../fetch/user/buy.js'

import * as storeActionsFromFile from '../../../actions/store'

import BuyAndStore from '../../../components/BuyAndStore'

class Buy extends React.Component {
    constructor(props, context) {
        super(props, context);
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this);
        this.state = {
            isStore: false
        }
    }
    render() {
        return (
            <BuyAndStore isStore={this.state.isStore} buyHandle={this.buyHandle.bind(this)} storeHandle={this.storeHandle.bind(this)}/>
        )
    }
    componentDidMount() {
        // 验证当前商户是否收藏
        this.checkStoreState()
    }
    randomNum(n,m){

        var c = m-n+1;

        return Math.floor(Math.random() * c + n);

    }
        // 检验当前商户是否被收藏
    checkStoreState() {
        const id = this.props.id
        const store = this.props.store

        store.forEach(item => {
            if (item.id === id) {
                // 已经被收藏
                this.setState({
                    isStore: true
                })
                return false
            }
        })
    }
    // 检查登录状态
    loginCheck() {
        const id = this.props.id
        const userinfo = this.props.userinfo
        if (!userinfo.username) {
            // 跳转到登录页面的时候，要传入目标router，以便登录完了可以自己跳转回来
            hashHistory.push('/Login/' + encodeURIComponent('/detail/' + id))
            return false
        }
        return true
    }
    // 购买事件
    buyHandle() {
        // 验证登录，未登录则retur
        const loginFlag = this.loginCheck()
        if (!loginFlag) {
            return
        }

        // 此过程为模拟购买，因此可省去复杂的购买过程
        const id = this.props.id
        const userinfo = this.props.userinfo
        console.log(userinfo);

        console.log(id);

        var num = 1;
        //消费价格 （50元 － 800元）
        var price = this.randomNum(50,800);

        var result = postBuy(userinfo.username,id,num,price,userinfo.token);
        result.then(res => {
            return res.json()
        }).then(json => {
            if (json.errno === 0) {
                alert(json.msg);
                // 跳转到用户主页
                hashHistory.push('/User')
            }
            else {
                alert(json.msg);
            }
        })

    }
    // 收藏事件
    storeHandle() {
        // 验证登录，未登录则return
        const loginFlag = this.loginCheck()
        if (!loginFlag) {
            return
        }

        const id = this.props.id
        const storeActions = this.props.storeActions
        if (this.state.isStore) {
            // 已经被收藏了，则取消收藏
            storeActions.rm({id: id})
        } else {
            // 未收藏，则添加到收藏中
            storeActions.add({id: id})
        }
        // 修改状态
        this.setState({
            isStore: !this.state.isStore
        })
    }
}

function mapStateToProps(state) {
    return {
        userinfo: state.userinfo,
        store: state.store
    }
}

function mapDispatchToProps(dispatch) {
    return {
        storeActions: bindActionCreators(storeActionsFromFile, dispatch)
    }
}
export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Buy)