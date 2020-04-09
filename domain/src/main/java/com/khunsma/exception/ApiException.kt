package com.khunsma.exception

class ApiException(val errorMessage: String): Exception(errorMessage)