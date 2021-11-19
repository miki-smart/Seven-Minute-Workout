package com.SevenMinuteWorkout

object Constants {
    fun defaultExerciseList():ArrayList<ExerciseClass> {
        val exerciseList = ArrayList<ExerciseClass>()
        val jumpigjuks = ExerciseClass(
            1,
            "Jumping jucks",
            R.drawable.ic_jumping_jacks,
            false, false
        )
        exerciseList.add(jumpigjuks)
        val abdominalcrunch = ExerciseClass(
            2,
            "Abdominal Crunch",
            R.drawable.ic_abdominal_crunch,
            false, false
        )
        exerciseList.add(abdominalcrunch)
        val highkness = ExerciseClass(
            3,
            "High Knees running in PLace",
            R.drawable.ic_high_knees_running_in_place,
            false, false
        )
        exerciseList.add(highkness)
        val lunge = ExerciseClass(
            4,
            "lunge",
            R.drawable.ic_lunge,
            false, false
        )
        exerciseList.add(lunge)
        val plank = ExerciseClass(5,"plank", R.drawable.ic_plank,false, false)
        exerciseList.add(plank)

        val pushupandRotation = ExerciseClass(6,"Pushup and Rotation", R.drawable.ic_push_up_and_rotation,false, false)
        exerciseList.add(pushupandRotation)

        val sidePlank = ExerciseClass(7,"Side Plank", R.drawable.ic_side_plank,false, false)
        exerciseList.add(sidePlank)

        val squat = ExerciseClass(8,"Squat", R.drawable.ic_squat,false, false)
        exerciseList.add(squat)

        val stepUpOnto = ExerciseClass(9,"Step Up Onto Chair", R.drawable.ic_step_up_onto_chair,false, false)
        exerciseList.add(stepUpOnto)

        val tricepsDiponChair = ExerciseClass(10,"Triceps Dip On Chair", R.drawable.ic_triceps_dip_on_chair,false, false)
        exerciseList.add(tricepsDiponChair)

        val wallSit = ExerciseClass(11,"Wall Sit", R.drawable.ic_wall_sit,false, false)
        exerciseList.add(wallSit)
        return exerciseList
    }
}