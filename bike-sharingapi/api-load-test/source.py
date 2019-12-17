from locust import HttpLocust, TaskSet, task, between

path_info_data = {
    "currentLatitude": "47.184463",
    "currentLongitude": "27.581588"
}

bicycle_data = {
}

headers = {'Content-Type': 'application/json', 'Accept': 'application/json'}


class WebsiteTasks(TaskSet):
    def on_start(self):
        self.client.get("/bicycles")

    @task
    def get_locations(self):
        self.client.get("/locations")

    @task
    def get_bicycles(self):
        self.client.get("/bicycles")

    @task
    def get_path_info(self):
        self.client.get("/path-info", json=path_info_data, headers=headers)

    @task
    def post_bicycle(self):
        self.client.post("/bicycle", json=bicycle_data, headers=headers)


class WebsiteUser(HttpLocust):
    task_set = WebsiteTasks
    wait_time = between(5, 15)
