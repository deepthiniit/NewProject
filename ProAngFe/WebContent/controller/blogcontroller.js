/**
 * 
 */

app
		.controller(
				'BlogCtrl',
				function($scope, $location, $rootScope, BlogService) {
					console.log('Blog controller');
					$scope.addBlog = function(blog) {
						console.log('Blog controller');
						console.log(blog);
						console.log(blog.blogContent);
						BlogService
								.addBlog(blog)
								.then(
										function(response) {
											alert('BlogPost is added successfully and it is waiting for approval');
											$location.path('/home')
										}, function(response) {
											$rootScope.error = response.data
											if (response.status == 401)
												$location.path('/login')
										})

						if ($rootScope.loggedInUser.role == 'ADMIN') {
							BlogService
									.getBlogsWaitingForApproval()
									.then(
											function(response) {
												$scope.blogsWaitingForApproval = response.data;
												console
														.log('Waiting blogs '
																+ JSON
																		.stringify($scope.blogsWaitingForApproval));
												$location
														.path('/blogsnotapproved');
											},
											function(response) {
												$rootScope.error = response.data
												if (response.status == 401)
													$location.path('/login')
											})
						}

					}
					// getting not approved blogs
					console.log('Not approved function is called');
					if ($rootScope.loggedInUser.role == 'ADMIN') {
						console.log("User is admin");
						BlogService
								.getBlogsWaitingForApproval()
								.then(
										function(response) {
											$scope.blogsWaitingForApproval = response.data;
											console
													.log('Waiting blogs in not approved function '
															+ JSON
																	.stringify($scope.blogsWaitingForApproval));
											// $location.path('/blogsnotapproved');
										}, function(response) {
											$rootScope.error = response.data
											if (response.status == 401)
												$location.path('/login')
										})
					}

					// Getting approved blogs
					BlogService.getBlogsApproved().then(function(response) {
						$scope.blogsApproved = response.data;
						console.log('Approved blogs ' + $scope.blogsApproved);
						// console.log('Approved blogs
						// '+JSON.stringify($scope.blogsApproved));
						//$location.path('/blogsapproved');
					}, function(response) {
						$rootScope.error = response.data
						if (response.status == 401)
							$location.path('/login')
					})

				})