package com.example.databasescomparison.data.model

data class DbTimer(
    var sqlohTime: Long,
    var roomTime: Long,
    var realmTime: Long,
    var objectBoxTime: Long
) {
    constructor() : this(0, 0, 0, 0)
}
