package com.geveze.models

import java.io.Serializable

/**
 * Created by sametoztoprak on 14.01.2018.
 */
class Status : Serializable {
    var image: ByteArray? = null
    var name: String? = null
    var date: String? = null
    var status: String? = null

    constructor(image: ByteArray?, name: String?, date: String?, status: String?) {
        this.image = image
        this.name = name
        this.date = date
        this.status = status
    }
}