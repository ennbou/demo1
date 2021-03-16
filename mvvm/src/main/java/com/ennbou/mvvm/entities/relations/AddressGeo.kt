package com.ennbou.mvvm.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.ennbou.mvvm.entities.Address
import com.ennbou.mvvm.entities.Geo

data class AddressGeo(
    @Embedded
    var address: Address,
    @Relation(
        parentColumn = "id",
        entityColumn = "addressId"
    )
    var geo: Geo
)
