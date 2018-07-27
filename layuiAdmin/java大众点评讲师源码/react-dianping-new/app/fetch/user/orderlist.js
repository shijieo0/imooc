import { get } from '../get'
import { post } from '../post'

export function getOrderListData(username) {
    const result = get('/api/orderlist/' + username)
    return result
}


export function postComment(id, comment, star,token,username) {
    const result = post('/api/submitComment', {
        id: id,
        comment: comment,
        star: star,
        token: token,
        username: username
    })
    return result
}