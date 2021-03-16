package com.ennbou.mvvm.entities

data class UserDetails(
    var id: Long,
    var name: String,
    var username: String,
    var email: String,
    var phone: String,
    var website: String,
    var company: Company2?,
    var address: Address2?
)

data class Company2(
    var name: String,
    var catchPhrase: String,
    var bs: String,
    var userId: Long
)

data class Address2(
    var street: String,
    var suite: String,
    var city: String,
    var zipcode: String,
    var geo: Geo2,
    var userId: Long
)


data class Geo2(var lat: Float, var lng: Float)


