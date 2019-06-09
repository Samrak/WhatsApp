package com.geveze.models

import java.io.Serializable

/**
 * Created by sametoztoprak on 14/12/2017.
 */

class Country : Serializable {
    var countryCode: String? = null
    var countryName: String? = null
    var countryISO: String? = null

    constructor() {}

    constructor(countryCode: String, countryName: String, countryISO: String) {
        this.countryCode = countryCode
        this.countryName = countryName
        this.countryISO = countryISO
    }
}
