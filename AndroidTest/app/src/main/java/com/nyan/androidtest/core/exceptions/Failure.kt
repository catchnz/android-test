package com.nyan.androidtest.core.exceptions

sealed class Failure {
    class NetworkConnection : Failure()
    class ServerError : Failure()
}