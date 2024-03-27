package kurmakaeva.anastasia.ui

enum class ScreenType(val title: Int) {
    Dashboard(title = R.string.dashboardTitle),
    Saved(title = R.string.savedTitle),
    AddGoal(title = R.string.addGoalTitle),
    AddSaved(title = R.string.addPresetTitle),
    AddInput(title = R.string.addInputTitle)
}