/**
 * 
 */

app.controller(
				'notificationCtrl',
				function($scope, $location, $rootScope, $routeParams,
						NotificationService) {
					var id = $routeParams.id;
					function getNotificationsNotViewed() {
						console.log("id in notification ctrler is " + id);
						NotificationService
								.getNotificationsNotViewed()
								.then(
										function(response) {
											$rootScope.notifications = response.data;
											$rootScope.notificationCount = $rootScope.notifications.length;
										}, function(response) {
											$rootScope.error = response.data
											if (response.status == 401)
												$location.path('/login')
										})
					}

					if (id != undefined) {
						console.log("id in notification ctrler is " + id);
						NotificationService.getNotifications(id).then(
								function(response) {
									$scope.notification = response.data
								}, function(response) {
									$rootScope.error = response.data
									if (reponse.status == 401)
										$location.path('/login')
								})
						NotificationService.updatenNotification(id).then(
								function(response) {
									getNotificationsNotViewed()
								}, function(response) {
									$rootScope.error = response.data
									if (reponse.status == 401)
										$location.path('/login')
								})
					}
					getNotificationsNotViewed()

				})