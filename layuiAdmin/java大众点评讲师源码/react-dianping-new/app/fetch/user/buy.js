import { get } from '../get'
import { post } from '../post'

export function postBuy(username,id,num,price,token) {
    const result = post('/api/order', {
        username: username,
        id:id,
        token:token,
        num:num,
        price:price
    })

    return result
}
