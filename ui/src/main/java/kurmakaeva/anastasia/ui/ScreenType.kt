package kurmakaeva.anastasia.ui

enum class ScreenType(val title: String) {
    Dashboard(title = "Today's goal"),
    Saved(title = "Saved presets"),
    AddGoal(title = "Add goal"),
    AddSaved(title = "New preset"),
    AddInput(title = "New input")
}