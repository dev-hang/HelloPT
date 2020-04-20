	var mon = moment().date(1).format("MM");
	console.log("월 : " + moment().date(1).format("MM"));
	
	var strData;
	
	makeCalendar(mon, userName);
	
	 
	
	function makeCalendar(mon, userName) {
		$.ajax({
			url : "selectMonth",
			data : {
				month : mon,
				fkUserId : userName
			},
			type : "post",
			dataType : 'json',
			success : function(data) {
				//alert("성공");
				console.log(data);

				// 참고사항------------------------------

				// 응답받은 데이터 형식 : [{}, {}, ... , {}] - 배열
				//strData = JSON.stringify(data); // JSON -> string
				strData = data;
				console.log("컨트롤러에서 받은 json데이터" + strData +" ");
					
				 monthData();
				 
				 //$("#cal").html('<img id="cal" class="calPop" src="/hellopt/resources/images/calendar/이벤트pop2.jpg">');

			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("통신에러");
			}

		}); 
	}
	
window.onload = function() {
	 
	 
  var today = moment(); // 현재 시간 구하기

  // 한글로 표시할 경우 (영어로 하려면 아래 lang부분 삭제)
  moment.lang("ko", {
    weekdays: [
      "일요일",
      "월요일",
      "화요일",
      "수요일",
      "목요일",
      "금요일",
      "토요일"
    ],
    weekdaysShort: ["일", "월", "화", "수", "목", "금", "토"]
  });

  /* 현재시간과의 차이 구할때
    moment("20111031", "YYYYMMDD").fromNow(); // 5 years ago
    moment("20120620", "YYYYMMDD").fromNow(); // 5 years ago
    moment().startOf('day').fromNow();        // 19 hours ago
    moment().endOf('day').fromNow();          // in 5 hours
    moment().startOf('hour').fromNow();       // 34 minutes ago
  */

  var ptMonth; // 현재 출력중인 달
  var ptDay; // 현재 클릭된 날짜
  var todayEvent = false; // 오늘날짜의 이벤트가 있는지 확인할 플래그

  
  //달력 만드는 부분 ( 달력, 이벤트 ) 
  function Calendar(selector, events) {
    this.el = document.querySelector(selector); 
    this.events = events;
    this.current = moment().date(1);
    this.draw();
    var current = document.querySelector(".today"); // 오늘날짜

    if (current) {
      var self = this;
      window.setTimeout(function() {
        self.openDay(current);
      }, 500);
    }
  }

  // 달력 그리기
  Calendar.prototype.draw = function() {
    // 달력 헤더 생성
    this.drawHeader();

    // 달력 월 생성
    this.drawMonth();

    //this.drawLegend();
  };

  // 달력 헤더 생성
  Calendar.prototype.drawHeader = function() {
    var self = this;
    //☆☆☆ 어디선가 header가 선언 되면?
    if (!this.header) {
      //Create the header elements
      this.header = createElement("div", "header"); // <div class='header'>생성
      this.header.className = "header";

      this.title = createElement("h1"); // 월/년 표시할 <h1> 생성

      var right = createElement("div", "right"); // <div class='right'> 생성
      right.addEventListener("click", function() {
        self.nextMonth();
      }); // right 클릭시 nextMonth()를 실행

      var left = createElement("div", "left"); // <div class='right'> 생성
      left.addEventListener("click", function() {
        self.prevMonth();
      }); // left 클릭시 prevMonth()를 실행

      // 달력 타이틀 태그 생성
      this.header.appendChild(this.title);
      //this.header.appendChild(right);
      //this.header.appendChild(left);
      this.el.appendChild(this.header);
    }
    this.title.innerHTML = this.current.format("YYYY년 MM월");
    console.log("달력 Title : ", this.current.format("YYYY년 MM월"));
    // 현재 월 확인 -> DB에서 월 조회 후 등록된 리스트 가져오기
    console.log(this.current.format("MM") + "월"); // ex. 02. 03 ..
    
    
 // json 위치 상단으로 변경 (스크립트 실행 시 데이터를 먼저 가져와야 함)-------------------------------------------------   
 /*
    $.ajax({
    	url : "selectMonth", 
    	data : { month : this.current.format("MM") },
    	type : "post",
    	success : function(data){
    		alert("성공");
    		console.log(data);
    		
    		//참고사항------------------------------
    		
    		//응답받은 데이터 형식 : [{}, {}, ... , {}] - 배열
			var strData = JSON.stringify(data); //JSON -> string
			console.log("-" + strData + "-");
    		
    		var jsData = 
    		console.log("-" + jsData + "-");
    		

    		
    	},
    	error : function(jqXHR, textStatus, errorThrown){
    		alert("통신에러" );
    	}
    	
    	
    }); 
*/    
//json 끝 -----------------------------------------------------------------------------------------------
 
  };

  
  
  
  
  // 달력 월 생성
  Calendar.prototype.drawMonth = function() {
    var self = this;

    // DB에 등록된 날짜에 맞춰 달력에 추가
    this.events.forEach(function(ev) {
    	
      console.log("이벤트 리스트 : " + ev.yymmdd);
      ev.date = self.current.clone().date(ev.yymmdd);
    });

    if (this.month) {
      console.log("month : ", this.month);
      this.oldMonth = this.month;
      this.oldMonth.className = "month out " + (self.next ? "next" : "prev");
      this.oldMonth.addEventListener("webkitAnimationEnd", function() {
        self.oldMonth.parentNode.removeChild(self.oldMonth);
        self.month = createElement("div", "month");
        self.backFill();
        self.currentMonth();
        self.fowardFill();
        self.el.appendChild(self.month);
        window.setTimeout(function() {
          self.month.className = "month in " + (self.next ? "next" : "prev");
        }, 16);
      });
    } else {
      this.month = createElement("div", "month");
      this.el.appendChild(this.month);
      this.backFill();
      this.currentMonth();
      this.fowardFill();
      this.month.className = "month new";
    }
  };

  Calendar.prototype.backFill = function() {
    var clone = this.current.clone();
    var dayOfWeek = clone.day();

    if (!dayOfWeek) {
      return;
    }

    clone.subtract("days", dayOfWeek + 1);

    for (var i = dayOfWeek; i > 0; i--) {
      this.drawDay(clone.add("days", 1));
    }
  };

  Calendar.prototype.fowardFill = function() {
    var clone = this.current
      .clone()
      .add("months", 1)
      .subtract("days", 1);
    var dayOfWeek = clone.day();

    if (dayOfWeek === 6) {
      return;
    }

    for (var i = dayOfWeek; i < 6; i++) {
      this.drawDay(clone.add("days", 1));
    }
  };

  Calendar.prototype.currentMonth = function() {
    var clone = this.current.clone();

    while (clone.month() === this.current.month()) {
      this.drawDay(clone);
      clone.add("days", 1);
    }
  };

  Calendar.prototype.getWeek = function(day) {
    if (!this.week || day.day() === 0) {
      this.week = createElement("div", "week");
      this.month.appendChild(this.week);
    }
  };

  // 달력 날짜 만들기
  Calendar.prototype.drawDay = function(day) {
    var self = this;
    this.getWeek(day);

    //Outer Day
    var outer = createElement("div", this.getDayClass(day));
    outer.addEventListener("click", function() {
      self.openDay(this);
    });

    //Day Name
    var name = createElement("div", "day-name", day.format("ddd"));

    //Day Number
    var number = createElement("div", "day-number", day.format("DD"));

    //Events
    var events = createElement("div", "day-events");
    this.drawEvents(day, events);

    outer.appendChild(name);
    outer.appendChild(number);
    outer.appendChild(events);
    this.week.appendChild(outer);
  };

  // 이벤트가 있는 날짜에 아이콘 표시
  Calendar.prototype.drawEvents = function(day, element) {
    if (day.month() === this.current.month()) {
      var todaysEvents = this.events.reduce(function(memo, ev) {
        if (ev.date.isSame(day, "yymmdd")) {
          //console.log("기록된 날짜 : " + ev.day);
          //console.log(day.format("DD") + "일 이벤트 : " + JSON.stringify(memo));
          memo.push(ev);
        }
        // 오늘 날짜에 리스트 없을 경우 글쓰기 기능 추가
        if (today.format("DD") == ev.yymmdd ) {
          todayEvent = true;
        }
        return memo;
      }, []);

      todaysEvents.forEach(function(ev) {
        var evSpan = createElement("span", "yellow");
        element.appendChild(evSpan);
      });
    }
  };
  
  Calendar.prototype.getDayClass = function(day) {
    classes = ["day"];
    if (day.month() !== this.current.month()) {
      classes.push("other");
      //오늘날짜 = 
    } else if (today.isSame(day, "day")) {
      classes.push("today");
      console.log(day);
    }
    return classes.join(" ");
  };

  Calendar.prototype.openDay = function(el) {
    var details, arrow;
    var dayNumber =
      +el.querySelectorAll(".day-number")[0].innerText ||
      +el.querySelectorAll(".day-number")[0].textContent;
    var day = this.current.clone().date(dayNumber);

    var currentOpened = document.querySelector(".details");
    console.log("클릭한날짜", this.current.format("MM") + "월", dayNumber + "일");
    ptMonth = this.current.format("MM");
    ptDay = day.format("DD");
    
    // 달력날짜중에서 오늘 날짜랑 같은 곳 && 이벤트 없는 곳
    if(ptDay == today.format("DD") && !todayEvent && userName.length >= 1  ) {
      var con = confirm("오늘의 운동을 기록하시겠습니까?");
      
      if (con == true){
    	  window.open("/hellopt/calenderWrite", 'calender', 'width=590 ,height=380, left=600, top=230 '); 
      } else if (con == false) {
    	  return false;
      }
    }

    //Check to see if there is an open detais box on the current row
    if (currentOpened && currentOpened.parentNode === el.parentNode) {
      details = currentOpened;
      arrow = document.querySelector(".arrow");
    } else {
      //Close the open events on differnt week row
      //currentOpened && currentOpened.parentNode.removeChild(currentOpened);
      if (currentOpened) {
        currentOpened.addEventListener("webkitAnimationEnd", function() {
          currentOpened.parentNode.removeChild(currentOpened);
        });
        currentOpened.addEventListener("oanimationend", function() {
          currentOpened.parentNode.removeChild(currentOpened);
        });
        currentOpened.addEventListener("msAnimationEnd", function() {
          currentOpened.parentNode.removeChild(currentOpened);
        });
        currentOpened.addEventListener("animationend", function() {
          currentOpened.parentNode.removeChild(currentOpened);
        });
        currentOpened.className = "details out";
      }

      // 클릭한 날짜에 디테일목록 생성
      details = createElement("div", "details in");

      // 디테일목록 안에 리스트 출력
      var arrow = createElement("div", "arrow");

      //Create the event wrapper
      details.appendChild(arrow);
      console.log("디테일리스트 : ", details);

      el.parentNode.appendChild(details);
    }

    var todaysEvents = this.events.reduce(function(memo, ev) {
      if (ev.date.isSame(day, "day")) {
        memo.push(ev);
        console.log("달력 이벤트 5 : "+ev);
      }
      return memo;
    }, []);
    this.renderEvents(todaysEvents, details);

    arrow.style.left = el.offsetLeft - el.parentNode.offsetLeft + 27 + "px";
  };

  Calendar.prototype.renderEvents = function(events, ele) {
    //Remove any events in the current details element
    var currentWrapper = ele.querySelector(".events");
    var wrapper = createElement(
      "div",
      "events in" + (currentWrapper ? " new" : "")
    );

    events.forEach(function(ev) {
      var div = createElement("div", "event");
      var square = createElement("div", "event-category " + ev.color);
      var span = createElement("span", "", "");
      var calIdx = $('.idx');
      var calidx2 = "${calendar.calendarIdx)" ;
      span.innerHTML = '<a class="eventA" href="#">'+ev.content+'</a>';
      ///hellopt/meetingRead
      span.addEventListener("click", function() {
        //alert(ptMonth + "월 " + ptDay + "일 글로 이동~@");
        window.open("/hellopt/calendarOne?calendarIdx="+ev.calendarIdx+"", 'onealender', 'width=590 ,height=680, left=600, top=230 ')
        opener.document.location.href='/hellopt/calender';
      });

      div.appendChild(square);
      div.appendChild(span);
      wrapper.appendChild(div);
    });

    if (!events.length) {
      var div = createElement("div", "event empty");
      var span = createElement("span", "", "운동 기록 없음");
      div.appendChild(span);
      wrapper.appendChild(div);
    } 

    if (currentWrapper) {
      currentWrapper.className = "events out";
      currentWrapper.addEventListener("webkitAnimationEnd", function() {
        currentWrapper.parentNode.removeChild(currentWrapper);
        ele.appendChild(wrapper);
      });
      currentWrapper.addEventListener("oanimationend", function() {
        currentWrapper.parentNode.removeChild(currentWrapper);
        ele.appendChild(wrapper);
      });
      currentWrapper.addEventListener("msAnimationEnd", function() {
        currentWrapper.parentNode.removeChild(currentWrapper);
        ele.appendChild(wrapper);
      });
      currentWrapper.addEventListener("animationend", function() {
        currentWrapper.parentNode.removeChild(currentWrapper);
        ele.appendChild(wrapper);
      });
    } else {
      ele.appendChild(wrapper);
    }
  };

  // 달력 하단
  // Calendar.prototype.drawLegend = function() {
  //   var legend = createElement('div', 'legend');
  //   var calendars = this.events.map(function(e) {
  // 	return e.calendar + '|' + e.color;
  //   }).reduce(function(memo, e) {
  // 	if(memo.indexOf(e) === -1) {
  // 	  memo.push(e);
  // 	}
  // 	return memo;
  //   }, []).forEach(function(e) {
  // 	var parts = e.split('|');
  // 	var entry = createElement('span', 'entry ' +  parts[1], parts[0]);
  // 	legend.appendChild(entry);
  //   });
  //   this.el.appendChild(legend);
  // }

  Calendar.prototype.nextMonth = function() {
    this.current.add("months", 1);
    this.next = true;
    
//	$.ajax({
//		url : "selectMonth",
//		data : {
//			month : this.current.format("MM"),
//			fkUserId : userName
//		},
//		type : "post",
//		dataType : 'json',
//		success : function(data) {
//			strData = data;
//			
//			var calendar = new Calendar("#eventCalendar", strData);
//		},
//		error : function(jqXHR, textStatus, errorThrown) {
//			//alert("통신에러");
//		}
//	});
	
    this.draw();
  };

  Calendar.prototype.prevMonth = function() {
    this.current.subtract("months", 1);
    this.next = false;
    
//	$.ajax({
//		url : "selectMonth",
//		data : {
//			month : this.current.format("MM"),
//			fkUserId : userName
//		},
//		type : "post",
//		dataType : 'json',
//		success : function(data) {
//			strData = data;
//			
//			var calendar = new Calendar("#eventCalendar", strData);
//		},
//		error : function(jqXHR, textStatus, errorThrown) {
//			//alert("통신에러");
//		}
//	}); 
	
    this.draw();
  };

  window.Calendar = Calendar;

  function createElement(tagName, className, innerText) {
    var ele = document.createElement(tagName);
    if (className) {
      ele.className = className;
    }
    if (innerText) {
      ele.innderText = ele.textContent = innerText;
    }
    return ele;
  }
  
  
  
  //monthData();
  
  
 };
 

// 월별 등록된 리스트 -> DB에서 월 조회후 데이터 담기게!
var monthData = function () {

	/*
	var strData = [

	{
		content : "요가",
		day : "29",
		color : "red"
	} ];
	*/

  function addDate(ev) {}
	
  // <div id='calendar'>에 리스트 추가
 var calendar = new Calendar("#eventCalendar", strData);
};


