package com.geveze.models

import java.io.Serializable

/**
 * Created by sametoztoprak on 17/12/2017.
 */
class Call : Serializable {
    var image: ByteArray? = null
    var name: String? = null
    var date: String? = null

    constructor(image: ByteArray?, name: String?, date: String?) {
        this.image = image
        this.name = name
        this.date = date
    }
}