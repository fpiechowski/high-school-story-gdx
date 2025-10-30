package pro.piechowski.highschoolstory.place

import pro.piechowski.highschoolstory.asset.AssetIdentifiers
import pro.piechowski.highschoolstory.map.EndlessMap
import pro.piechowski.highschoolstory.map.Map
import pro.piechowski.highschoolstory.physics.mps

object Road : Place("Road", EndlessMap(AssetIdentifiers.Maps.Road, EndlessMap.Orientation.HORIZONTAL))
