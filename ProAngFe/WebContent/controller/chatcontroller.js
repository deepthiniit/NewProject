/**
 * 
 */
app.controller('ChatCtrl',
		function($rootScope, $scope, $location, ChatService) {
			alert('entering chat controller')
			$scope.chats = [];
			$scope.stompClient = ChatService.stompClient;
			$scope.users = [];
			$scope.$on('sockConnected', function(event, frame) {
				alert('sockconnected')
				$scope.userName = $rootScope.loggedInUser.firstname;
				$scope.stompClient.subscribe("/topic/join" + $scope.userName,
						function(message) {
							console.log("Message = " + message);
							user = JSON.parse(message.body);
							console.log(user)

							if (user != $scope.userName
									&& $.inArray(user, $scope.users) == -1) {
								$scope.addUser(user);
								$scope.latestUser = user;
								$scope.$apply();
								$('#joinedChat').fadeIn(500).delay(2000)
										.fadeOut(500);
							}

						});

				$scope.stompClient.subscribe('/app/join/' + $scope.userName,
						function(message) {

							$scope.users = JSON.parse(message.body);

							$scope.$apply();
						});

			});

			$scope.sendMessage = function(chat) {
				chat.from = $scope.userName;

				$scope.stompClient.send('/app/chat', {}, JSON.stringify(chat));
				console.log(chat);
				$rootScope.$broadcast('sendingChat', chat);
				$scope.chat.message = "";

			};

			$scope.capitalize = function(str) {
				console.log(str);
				return str.charAt(0).toUpperCase() + str.slice(1);
			};

			$scope.addUser = function(user) {
				$scope.users.push(user);
				$scope.$apply();
			};
			$scope.$on('sockConnected', function(event, frame) {
				// $scope.userName=$rootScope.loggedInUser.email;

				$scope.userName = $rootScope.loggedInUser.firstname;

				$scope.stompClient.subscribe("/queue/chats/" + $scope.userName,
						function(message) {

							$scope.processIncomingMessage(message, false);
						});

				$scope.stompClient.subscribe("/queue/chats", function(message) {

					$scope.processIncomingMessage(message, true);
				});

			});

			$scope.$on('sendingChat', function(event, sentChat) {
				chat = angular.copy(sentChat);
				chat.from = 'Me';
				chat.direction = 'outgoing';
				$scope.addChat(chat);
			});

			$scope.processIncomingMessage = function(message, isBroadcast) {
				message = JSON.parse(message.body);
				message.direction = 'incoming';
				message.broadcast = isBroadcast
				if (message.from != $scope.userName) {
					$scope.addChat(message);
					$scope.$apply(); // since inside subscribe closure
				}
			};

			ChatService.users.then(function(response) {
				$scope.users = response.data;
				console.log("friend status " + JSON.stringify($scope.users));

				for (var i = 0; i < $scope.users.length; i++) {
					console.log($scope.users[i].firstname);
				}

			}, function(response) {
				$rootScope.error = response.data
				if (response.status == 401)
					$location.path('/login')

			})

			$scope.addChat = function(chat) {
				$scope.chats.push(chat);
			};

		});