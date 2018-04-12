package pinch.bubble

/**
 * Created by timkranen on 3/26/18.
 *
 * This is a wrapper class to send over data through observables. This can either
 * hold the actual data or a state such as loading/error.
 */
class Resource<T>(val status: Status) {
    var data: T? = null
    var error: String? = null

    constructor(status: Status, data: T?) : this(status) {
        this.data = data
    }

    constructor(status: Status, error: String?) : this(status) {
        this.error = error
    }
}