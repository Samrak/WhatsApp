package com.geveze.models

import java.io.Serializable

/**
 * Created by sametoztoprak on 16/12/2017.
 */

class Friend : Serializable {
    var image: ByteArray? = null
    var name: String? = null
    var status: String? = null

    constructor(image: ByteArray?, name: String?, status: String?) {
        this.image = image
        this.name = name
        this.status = status
    }
}
