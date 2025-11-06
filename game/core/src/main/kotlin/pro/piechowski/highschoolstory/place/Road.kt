package pro.piechowski.highschoolstory.place

import pro.piechowski.highschoolstory.asset.AssetIdentifiers
import pro.piechowski.highschoolstory.map.RepeatingMap

object Road : Place("Road", RepeatingMap(AssetIdentifiers.Maps.Road, RepeatingMap.Orientation.HORIZONTAL))
