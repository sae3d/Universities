package com.xische.common.misc


typealias Completable = Result<Unit>

suspend fun <T> Result<T>.flatMapDelayError(
    transform: suspend (T) -> T,
    elseTransform: suspend () -> Result<T>
): Result<T> {
    return if (isSuccess) {
        map { transform(it) }
    } else {
        val result = elseTransform()
        return if (result.isSuccess) {
            result
        } else this
    }

}

suspend fun <T> Completable.andThen(
    transform: suspend () -> Result<T>,
): Result<T> {
    return if (isSuccess) {
        transform()
    } else {
        Result.failure(this.exceptionOrNull() ?: Exception())
    }
}
