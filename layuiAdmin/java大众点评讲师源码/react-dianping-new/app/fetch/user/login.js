import { get } from '../get'
import { post } from '../post'

export function postPhone(username) {
   // const result = post('/api/sms')

    const result = post('/api/sms', {
        username: username
    })

    return result
}


export function postLogin(username,code) {
    const result = post('/api/login', {
        username: username,
        code:code
    })

    return result
}
