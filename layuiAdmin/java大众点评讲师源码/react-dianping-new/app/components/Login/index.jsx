import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'

import './style.less'

class Login extends React.Component 
{
    constructor(props, context) {
        super(props, context);
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this);
        this.state = {
            username: ''
        }
    }
    render() {
        return (
            <div id="login-container">
                <div className="input-container phone-container">
                    <i className="icon-tablet"></i>
                    <input 
                        type="text" 
                        placeholder="输入手机号" 
                        onChange={this.changeHandle.bind(this)} 
                        value={this.state.username}
                    />
                </div>
                <div className="input-container password-container">
                    <i className="icon-key"></i>
                    <button onClick={this.sendSmsHandle.bind(this)}>发送验证码</button>
                    <input 
                        type="text" 
                        placeholder="输入验证码" 
                        onChange={this.changeCodeHandle.bind(this)} 
                        value={this.state.code}
                    />
                </div>
                <button className="btn-login" onClick={this.clickHandle.bind(this)}>登录</button>
            </div>
        )
    }
    changeCodeHandle(e) {
        this.setState({
            code: e.target.value
        })
    }
    changeHandle(e) {
        this.setState({
            username: e.target.value
        })
    }
    sendSmsHandle() {
        const username = this.state.username
        const sendSmsHandle = this.props.sendSmsHandle
        sendSmsHandle(username);

    }
    clickHandle() {
        const username = this.state.username
        const code = this.state.code
        const loginHandle = this.props.loginHandle
        loginHandle(username,code);

    }
}

export default Login