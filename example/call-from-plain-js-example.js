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

    console.log("next work day after 2012-01-01. expected: 2012-01-10, actual: " + service.move_to_workday(new Date(2012,1-1,1),false));
    console.log("previous work day after 2011-12-30. expected: 2012-01-10, actual: " + service.move_to_workday_backwards(new Date(2012,1-1,1),true));

    console.log("add 2 work days to 2012-01-07. expected: 2012-01-12, actual: " + service.add_work_days(new Date(2012,1-1,7),2));

    console.log("work days count between 2012-01-01 and 2012-01-15. expected 4, actual: " + service.work_day_count(new Date(2012,1-1,1),new Date(2012,1-1,15)));

}

sample();
