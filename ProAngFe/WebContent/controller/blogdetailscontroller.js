/**
 * 
 */
app.controller('BlogDetailsCtrl', function($scope, $location, BlogService,
		$rootScope, $sce, $routeParams) {
	var id = $routeParams.id; // this is an id that we get in the url path i.e. lastpart of the url
	$scope.rejectionTxt = false;
	BlogService.getBlog(id).then(function(response) {
		console.log(response.data)
		$scope.blog = response.data
		$scope.content = $sce.trustAsHtml($scope.blog.blogContent)
	}, function(response) {
		$rootScope.error = response.data
		if (response.status == 401)
			$location.path('/login')

	})

	BlogService.hasUserLikedBlog(id).then(function(response) {

		if (response.data == "")
			$scope.isLiked = false
		else
			$scope.isLiked = true

	}, function(response) {
		$rootScope.error = response.data
		if (response.status == 401)
			$location.path('/login')
	})

	$scope.approve = function(blog) {

		BlogService.approve(blog).then(function(response) {
			$location.path('/blogsnotapproved')
		}, function(response) {
			$rootScope.error = response.error
			if (response.error == 401)
				$location.path('/login')
		})
	}

	$scope.reject = function(blog) {

		BlogService.reject(blog, $scope.rejectionReason).then(
				function(response) {
					$location.path('/blogsnotapproved')
				}, function(response) {
					$rootScope.error = response.error
					if (response.error == 401)
						$location.path('/login')
				});
	}
	$scope.showRejectionTxt = function(blog) {
		console.log('Rejecting blog');
		$scope.rejectionTxt = true;
		$scope.reject(blog);
	}

	$scope.updateLikes = function(id) {
		console.log('updating likes for blog ' + id);
		BlogService.updateLikes(id).then(function(response) {
			$scope.blog = response.data; //update blogpost likes
			$scope.isLike | $scope.isLiked
		}, function(response) {
			$rootScope.error = response.data
			if (response.status == 401)
				$location.path('/login')

		});
	}

	$scope.addComment = function(blogid, commentTxt) {
		$scope.blogComment = {}

		$scope.blogComment.commentTxt = commentTxt;

		BlogService.addComment(blogid, $scope.blogComment).then(
				function(response) {
					$scope.commentTxt = ''
					getBlogComments(id)
				}, function(response) {
					$rootscope.error = resonse.data
					if (response.status == 401)
						$location.path('/login')
					else {
						$scope.exceptionMessage = response.data
					}
				});
	}
	function getBlogComments(id) {
		BlogService.getBlogComments(id).then(function(response) {
			$scope.comments = response.data
		}, function(response) {
			$rootScope.error = response.data
			if (response.status == 401)
				$location.path('/login')
		});
	}
	getBlogComments(id);
})