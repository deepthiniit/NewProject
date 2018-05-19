/**
 * 
 */

app.controller('JobCtrl', function($scope, $rootScope, $location, JobService,
		$routeParams) {
	var id = $routeParams.id;
	//call this function from jobform.html
	$scope.addJob = function(job) {
		console.log(job.jobtittle)
		JobService.addjob(job).then(function(response) {
			alert('Job details posted successfully...');
			$location.path("/home")
		}, function(response) {
			//3points for return statement
			//1.not authenticated.. not authorised-401

			$rootScope.error = response.data
			if (response.status == 401)
				$location.path("/login")
		})
	}
	JobService.getAllJobs().then(function(response) {
		$scope.jobs = response.data
	}, function(response) {
		$rootScope.error = response.data
		if (response.status == 401)//not logged in
			$location.path('/login')
	})

	if (id != undefined) {
		JobService.getJob(id).then(function(response) {
			$scope.job = response.data
		}, function(response) {
			$rootScope.error = response.data
			if (response.status == 401) //not logged in
				$location.path('/login')

		})
	}
})