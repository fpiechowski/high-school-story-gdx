package pro.piechowski.highschoolstory.inspector

import javafx.collections.FXCollections
import javafx.collections.ObservableList

fun <T> List<T>.toObservableList(): ObservableList<T> = FXCollections.observableList(this)
