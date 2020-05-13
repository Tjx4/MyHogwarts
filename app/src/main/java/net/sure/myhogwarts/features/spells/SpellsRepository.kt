package net.sure.myhogwarts.features.spells

import net.sure.myhogwarts.features.base.repository.BaseRepository

class SpellsRepository : BaseRepository() {
    suspend fun getAllSpells(apiKey: String) = retrofitHelper?.spells(apiKey)
    suspend fun getSpell(spellId: String, apiKey: String) = retrofitHelper?.spell(spellId, apiKey)
}