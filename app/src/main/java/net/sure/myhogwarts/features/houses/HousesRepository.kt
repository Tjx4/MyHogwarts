package net.sure.myhogwarts.features.houses

import net.sure.myhogwarts.features.base.repository.BaseRepository

class HousesRepository : BaseRepository() {
    suspend fun getHouses(apiKey: String) = retrofitHelper?.houses(apiKey)
    suspend fun getHouse(apiKey: String, houseId: String) = retrofitHelper?.house(houseId, apiKey)
}