package com.restart.dataalarm

enum class NotificationType(val title: String, val id: Int) {
    NORMAL("일반알림",0),
    EXPANDABLE("확장형 알림",1),
    CUSTOM("커스텀 알림",3)
}