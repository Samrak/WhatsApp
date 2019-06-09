package com.geveze.models

import java.io.Serializable

/**
 * Created by sametoztoprak on 17/12/2017.
 */
class Chat : Serializable {
    var image: ByteArray? = null
    var name: String? = null
    var date: String? = null
    var sentence: String? = null

    constructor(image: ByteArray?, name: String?, date: String?, sentence: String?) {
        this.image = image
        this.name = name
        this.date = date
        this.sentence = sentence
    }
}