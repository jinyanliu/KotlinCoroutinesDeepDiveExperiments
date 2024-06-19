package org.example

import kotlinx.coroutines.*

class ApiException(
    val code: Int,
    message: String
) : Throwable(message)

fun getFollowersNumber2(): Int =
    throw ApiException(500, "Service unavailable")

suspend fun getUserDetails2(): Details = coroutineScope {
    val userName = async { getUserName() }
    val followersNumber = async { getFollowersNumber2() }
    Details(userName.await(), followersNumber.await())
}

fun main() = runBlocking {
    val details = try {
        getUserDetails2()
    } catch (e: ApiException) {
        null
    }
    val tweets = async { getTweets() }
    println("User: $details")
    println("Tweets: ${tweets.await()}")
}