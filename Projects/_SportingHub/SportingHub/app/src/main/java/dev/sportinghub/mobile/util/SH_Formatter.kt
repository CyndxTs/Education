package dev.sportinghub.mobile.client.features

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

object SH_Formatter {
    private val displayDateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val SqlDateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val displayLocalDateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    private val SqlLocalDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    private var log: Boolean = false

    enum class Clause {
        WHERE,
        JOIN,
        ORDER_BY
    }

    fun toggleLog() {
        this.log = !this.log
    }

    fun toSqlString(str: String,matchMode: Int = 0): String {
        return when(matchMode) {
            0 -> "'$str'"
            1 -> "'%$str%'"
            2 -> "'%$str'"
            else -> "'$str%'"
        }
    }

    fun toSqlString(b: Boolean?): String {
        return when(b) {
            true -> "true"
            false -> "false"
            null -> {
                if (this.log) println("SHF: Formato de 'Boolean' para 'SqlString' invalido. Devolviendo: false")
                "false"
            }
        }
    }

    fun toSqlString(d: Date?): String {
        return try {
            "'${this.SqlDateFormatter.format(d)}'"
        } catch (e: Exception) {
            if (this.log) println("SHF: Formato de 'Date' para 'SqlString' invalido. Devolviendo: 'null'")
            "'null'"
        }
    }

    fun toSqlString(ldt: LocalDateTime?): String {
        return try {
            "'${this.SqlLocalDateTimeFormatter.format(ldt)}"
        } catch (e: Exception) {
            if (this.log) println("SHF: Formato de 'LocalDateTime' para 'SqlString' invalido. Devolviendo: 'null'")
            "'null'"
        }
    }

    fun <T : Enum<T>> toSqlString(enum: T?): String {
        return try {
            "'${enum.toString()}'"
        } catch (e: Exception) {
            if (this.log) println("SHF: Formato de 'Enum' para 'SqlString' invalido. Devolviendo: 'null'")
            "'null'"
        }
    }

    fun toDisplayString(d: Date?): String {
        return try {
            this.displayDateFormatter.format(d)
        } catch (e: Exception) {
            if (this.log) println("SHF: Formato de 'Date' para 'DisplayString' invalido. Devolviendo: 'null'")
            "'null'"
        }
    }

    fun toDisplayString(ldt: LocalDateTime?): String {
        return try {
            this.displayLocalDateTimeFormatter.format(ldt)
        } catch (e: Exception) {
            if (this.log) println("SHF: Formato de 'LocalDateTime' para 'DisplayString' invalido. Devolviendo: 'null'")
            "'null'"
        }
    }

    fun <T : Enum<T>> toDisplayString(enum: T?): String {
        return try {
            "'${enum.toString().uppercase()}'"
        } catch (e: Exception) {
            if (this.log) println("SHF: Formato de 'Enum' para 'DisplayString' invalido. Devolviendo: 'null'")
            "'null'"
        }
    }

    fun toValidDate(str: String?): Date? {
        return try {
            this.displayDateFormatter.parse(str!!.trim())
        } catch (eD: Exception) {
            try {
                this.SqlDateFormatter.parse(str!!.trim())
            } catch (eS: Exception) {
                if (this.log) println("SHF: Formato de 'String' para 'ValidDate' invalido. Devolviendo: null")
                null
            }
        }
    }

    fun toValidLocalDateTime(str: String?): LocalDateTime? {
        return try {
            LocalDateTime.parse(str!!.trim(),this.displayLocalDateTimeFormatter)
        } catch (eD: Exception) {
            try {
                LocalDateTime.parse(str!!.trim(),this.SqlLocalDateTimeFormatter)
            } catch (eS: Exception) {
                if (this.log) println("SHF: Formato de 'String' para 'ValidLocalDateTime' invalido. Devolviendo: null")
                null
            }
        }
    }

    fun <T : Enum<T>> toValidEnum(str: String?, enumClass: Class<T>): T? {
        return try {
            java.lang.Enum.valueOf(enumClass, str!!.trim())
        } catch (e: Exception) {
            try {
                java.lang.Enum.valueOf(enumClass, str!!.trim().uppercase())
            } catch (eUC: Exception) {
                try {
                    java.lang.Enum.valueOf(enumClass, str!!.trim().lowercase())
                } catch (eLC: Exception) {
                    if (this.log) println("SHF: Formato de 'String' para 'ValidEnum' invalido. Devolviendo: null")
                    null
                }
            }
        }
    }

    fun isValidCondition(filterModel: String?): Boolean {
        return filterModel != null && !filterModel.isBlank()
    }

    fun isValidCondition(filterModel: Int?): Boolean {
        return  filterModel != null && filterModel >= 0
    }

    fun isValidCondition(filterModel: Float?): Boolean {
        return  filterModel != null && filterModel >= 0f
    }

    fun isValidCondition(filterModel: Double?): Boolean {
        return  filterModel != null && filterModel >= 0.0
    }

    fun <T> isValidCondition(filterModel: T?): Boolean {
        return filterModel != null
    }

    fun appendClause(sql: String,conditions: MutableList<String>,clause: Clause): String {
        var newQuery: String = sql
        if(conditions.size > 0) {
            newQuery += when(clause) {
                Clause.WHERE -> " WHERE " + conditions.joinToString(" AND ")
                Clause.JOIN -> conditions.joinToString(" "," ") { condition -> "JOIN $condition" }
                Clause.ORDER_BY -> " ORDER BY " + conditions.joinToString(", ")
            }
        }
        return newQuery
    }
}