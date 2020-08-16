$(function () {
	'use strict';

	var client;
	var scheduledPush;
	var subscribeToTopic;

	if (scheduledPush != null) {
		scheduledPush.addEventListener('message', function (event) {
			console.log('Message from server ', event.data);
		});
	}

	function showMessage(mesg) {
		$('#messages').prepend('<tr>' +
			'<td>' + mesg.from + '</td>' +
			'<td>' + mesg.topic + '</td>' +
			'<td>' + mesg.message + '</td>' +
			'<td>' + mesg.time + '</td>' +
			'</tr>');
	}

	function showScheduledPush(pushMessage) {
		console.log("Testing in and out the message : " + pushMessage);
		$('#scheduledPush').replaceWith('<p id="scheduledPush">' + pushMessage + '</p>');
	}

	function setConnected(connected) {
		$("#connect").prop("disabled", connected);
		$("#disconnect").prop("disabled", !connected);
		$('#from').prop('disabled', connected);
		$('#text').prop('disabled', !connected);
		if (connected) {
			$("#conversation").show();
			$('#text').focus();
		}
		else $("#conversation").hide();
		$("#messages").html("");
	}

	$("form").on('submit', function (e) {
		e.preventDefault();
	});

	$('#from').on('blur change keyup', function (ev) {
		$('#connect').prop('disabled', $(this).val().length == 0);
	});
	$('#connect,#disconnect,#text').prop('disabled', true);

	$('#connect').click(function () {

		console.log("Inside connect!");
		var ourUrl = "/rtcf?interests=myFirstInterest"
		client = Stomp.over(new SockJS(ourUrl));
		client.connect({}, function (frame) {
			setConnected(true);
			client.subscribe('/user/topic/messages', function (message) {
				showMessage(JSON.parse(message.body));
			});
		});

		scheduledPush = Stomp.over(new SockJS('/rtcf'));
		console.log("Connecting to scheduled push");
		scheduledPush.connect({}, function (frame) {
			scheduledPush.subscribe('/topic/scheduledpush', function (message) {
				showScheduledPush(message.body);
			});
		});
		console.log("leaving connect!");

	});

	$('#disconnect').click(function () {
		if (client != null) {
			client.disconnect();
			setConnected(false);
		}
		client = null;
	});

	$('#send').click(function () {
		var interests = ["firstInterest","secondInterest"];
		var topic = $('#topic').val();
		client.send("/app/interests/subscribe", {}, JSON.stringify({
			interests: interests,
			text: $('#text').val(),
		}));
		$('#text').val("");
	});

	$('#topic').change(function () {
		var topic = $('#topic').val();
		console.log("Inside client send method with topic as " + topic);
		client.send("/app/interests/subscribe", {}, JSON.stringify({
			from: $("#from").val(),
			text: '',
		}));
	});

});
