function check_date(service,
                    date,
                    expected_workday,
                    expected_weekend,
                    expected_holiday,
                    expected_special_workday) {
    console.log('check date: ' + date.getFullYear() + '-' + (date.getMonth()+1) + '-' + date.getDate());
    console.log('is working day. expected: '+ expected_workday +', actual: '
                + service.is_workday(date));
    console.log('is holiday. expected: ' + expected_holiday + ', actual:  '
                + service.is_holiday(date));
    console.log('is weekend. expected: ' + expected_weekend + ', actual:  '
                + service.is_weekend(date));
    console.log('is special working day. expected: ' + expected_special_workday + ', actual:  '
                + service.is_special_working_day(date));
}

function sample() {
    var service = cljs_workcalendar.api.createWorkCalendarService();

    check_date(service, new Date(2012,1-1,3), false, false, true, false);
    check_date(service, new Date(2012,1-1,14), false, true, false, false);
    check_date(service, new Date(2012,3-1,11), true, true, false, true);
    check_date(service, new Date(2012,2-1,1), true, false, false, false);
}

sample();
