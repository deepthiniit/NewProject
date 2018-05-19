/**
 * 
 */
/**
 * UserService
 * 1.registerUser(user)function
 * 
 * 
 */

app
		.controller(
				"UserController",
				function($scope, $rootScope, $location, UserService,
						$cookieStore) {
					$scope.registerUser = function(user) { //user is from view-registrationform.html
						console
								.log('entering usercontroller registerUser function in frontend '
										+ JSON.stringify(user))
						UserService
								.registerUser(user)
								.then(
										function(response) {//success [200,user]
											alert('Registered Successfully...please login again...')
											$location.path('/home')

										}, function(response) { //error[409 ErrorClass/500 ErrorClass]
											$scope.error = response.data
										})
					}

					$scope.login = function(user) {
						/* console.log('UserController->login')
						  console.log(user)*/
						UserService.login(user).then(function(response) {
							$rootScope.loggedInUser = response.data //user object
							$cookieStore.put('currentuser', response.data)
							/*console.log('success')
							console.log(response.data)*/

							$location.path('/home')
						}, function(response) {

							/*console.log('error')
							console.log(response.data)*/

							$scope.error = response.data
							$location.path('/login')

						})

					}

					//statement which will get executed automatically when controller gets loaded
					//controller to view
					//   if($rootScope.loggedInUser!=undefined){}
					//   UserService.getUser().then(
					//		   function(response){
					//			   $scope.user=response.data    //result of the query:select * from user wher email=?
					//		   },
					//			   
					//		   function(response){
					//			   if(response.status==401)
					//				   $location.path("/login")
					//				   else
					//					   $scope.error=response.data;
					//		   })

					//view to controller
					$scope.updateUser = function(user) {
						UserService.updateUser(user).then(function(response) {
							alter("updated user profile successfully..")
							$rootscope.loggedInUser = response.data
							$cookieStore.put("loggedInUser", response.data)
						}, function(response) {
							if (response.status == 401)
								$location.path('/login')
							else
								$scope.error = response.data

						})

					}

					$scope.logout = function(user) {

						UserService.logout(user).then(function(response) {
							$rootScope.loggedInUser = response.data //user object
							$cookieStore.put('currentuser', response.data)

							$location.path('/home')
						}, function(response) {
							$scope.error = response.data
							$location.path('/login')

						})

					}

				})