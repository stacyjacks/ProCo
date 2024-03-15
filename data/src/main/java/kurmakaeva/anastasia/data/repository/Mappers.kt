package kurmakaeva.anastasia.data.repository

import kurmakaeva.anastasia.data.db.model.GoalData
import kurmakaeva.anastasia.data.db.model.Input
import kurmakaeva.anastasia.data.db.model.Saved
import kurmakaeva.anastasia.domain.entities.GoalDataEntity
import kurmakaeva.anastasia.domain.entities.InputEntity
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

fun Input.toEntity(): InputEntity = InputEntity(
    id = id,
    input = input,
    time = time
)

fun InputEntity.toDao(): Input = Input(
    id = id,
    input = input,
    time = time
)

fun GoalData.toEntity(): GoalDataEntity = GoalDataEntity(
    current = current,
    goal = goal
)

fun GoalDataEntity.toDao(): GoalData = GoalData(
    current = current,
    goal = goal
)