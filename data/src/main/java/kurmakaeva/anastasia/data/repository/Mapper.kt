package kurmakaeva.anastasia.data.repository

import kurmakaeva.anastasia.data.db.Saved
import kurmakaeva.anastasia.domain.entities.SavedEntity

fun Saved.toEntity(): SavedEntity = SavedEntity(
    id = id,
    name = name,
    grams = grams
)

fun SavedEntity.toDao(): Saved = Saved(
    id = id,
    name = name,
    grams = grams
)