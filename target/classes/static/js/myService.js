/**
 * 根据来电获取详细信息
 * @param callPhone
 */
function getDetailsByPhone(callPhone, success) {
    ajaxPost(
        "/bg/GsMemberMain/selectByPhoneOrFixPhone.do",
        {"callPhone": callPhone},
        success
    )
}