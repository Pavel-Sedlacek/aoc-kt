package utils.common.automata.fetching

import utils.common.coordinates.ICoordinates

interface INeighbourFetchStrategy<Dim : ICoordinates> {
    fun <T> fetch(location: Dim, map: Map<Dim, T>, wrap: Boolean): Map<Dim, T>
}

