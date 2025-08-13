package pt.guilhermerodrigues.awesomenewsbroadcaster.core

sealed interface AsyncState<out SuccessValue> {
    data object Loading : AsyncState<Nothing>
    data class Success<SuccessValue>(val data: SuccessValue) : AsyncState<SuccessValue>
    data class Error(val throwable: Throwable) : AsyncState<Nothing>
}
