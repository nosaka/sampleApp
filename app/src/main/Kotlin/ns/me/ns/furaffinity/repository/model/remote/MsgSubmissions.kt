package ns.me.ns.furaffinity.repository.model.remote

import ns.me.ns.furaffinity.repository.model.AbstractBaseJsoupResponse
import ns.me.ns.furaffinity.repository.model.remote.entity.ViewElement

/**
 * MsgSubmissions
 */
class MsgSubmissions : AbstractBaseJsoupResponse() {

    val viewElements = ArrayList<ViewElement>()
}