package com.divesh.notes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class Note(val text: String) {
   @PrimaryKey(autoGenerate = true) var id = 0
}