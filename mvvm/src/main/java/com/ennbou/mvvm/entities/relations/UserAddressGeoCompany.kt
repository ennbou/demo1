package com.ennbou.mvvm.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.ennbou.mvvm.entities.Address
import com.ennbou.mvvm.entities.Company
import com.ennbou.mvvm.entities.User

data class UserAddressGeoCompany(
    @Embedded
    var user: User,
    @Relation(
        entity = Address::class,
        parentColumn = "id",
        entityColumn = "userId"
    )
    var address: AddressGeo,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    var company: Company
)