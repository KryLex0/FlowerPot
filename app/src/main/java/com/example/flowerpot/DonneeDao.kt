package com.example.flowerpot

import androidx.room.*

@Dao
interface DonneeDao {

    @Insert//(onConflict = OnConflictStrategy.REPLACE)
    fun insertDonnee(donnee: Donnee)
/*
    @Query("SELECT * FROM Players WHERE name LIKE :nomP AND " +
            "score LIKE :scoreP LIMIT 1")
    fun findPlayerByData(nomP: String, scoreP: Long): Donnee
*/
    /////////////////////////////////////////////////////////////////////////////////

    @Query("SELECT * FROM Donnees")
    fun getAll(): List<Donnee>

    @Query("SELECT * FROM Donnees WHERE id=(SELECT max(id) FROM Donnees)")
    fun getLastDonnee(): Donnee

    @Query("SELECT temperature FROM Donnees")
    fun getAllTemperature(): List<Double>

    @Query("SELECT humidite FROM Donnees")
    fun getAllHumidite(): List<Double>

    @Query("SELECT luminosite FROM Donnees")
    fun getAllLuminosite(): List<Int>


    @Query("SELECT max(id) FROM Donnees")
    fun getMaxId(): Int

    /*
    @Query("SELECT * FROM Players WHERE id=(SELECT max(id) FROM Players)")
    fun getLastScore(): Player

    @Query("SELECT * FROM Donnees WHERE score=(SELECT max(score) FROM Donnees)")
    fun getHighScore(): Donnee

    @Query("SELECT * FROM Donnees ORDER BY score DESC, name ASC LIMIT 10")
    fun getTenLastAll(): List<Donnee>


    @Query("SELECT * FROM Donnees WHERE difficulty LIKE 'Facile' ORDER BY score DESC, name ASC LIMIT 10")
    fun getTenLastFacile(): List<Donnee>

    @Query("SELECT * FROM Donnees WHERE difficulty LIKE 'Normal' ORDER BY score DESC, name ASC LIMIT 10")
    fun getTenLastNormal(): List<Donnee>

    @Query("SELECT * FROM Donnees WHERE difficulty LIKE 'Difficile' ORDER BY score DESC, name ASC LIMIT 10")
    fun getTenLastDifficile(): List<Donnee>
*/
/////////////////////////////////////////////////////////////////////////////////

    @Query("DELETE FROM Donnees WHERE id LIKE :playerId")
    fun deletePlayerFromId(playerId: Long)

    @Query("DELETE FROM Donnees")
    fun deleteAll()


}