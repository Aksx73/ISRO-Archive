package com.strings.attached.musiclibrary.util

import com.absut.isro.archive.data.model.Spacecraft

object TestUtil {

    fun createTestSpacecrafts(howMany: Int): List<Spacecraft> {
        val list = listOf(
            Spacecraft(1,"spacecraft 1"),
            Spacecraft(2,"spacecraft 2"),
            Spacecraft(3,"spacecraft 3"),
            Spacecraft(4,"spacecraft 4"),
            Spacecraft(5,"spacecraft 5"),
        )
        return list.take(howMany)
    }
}