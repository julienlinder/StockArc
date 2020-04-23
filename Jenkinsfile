pipeline {
    options {
        disableConcurrentBuilds()
    }
    agent any
	environment {
		SPRING_DATASOURCE_URL='jdbc:mysql://157.26.83.84:3306/spring_db_2020?useSSL=false'
		SPRING_DATASOURCE_USERNAME=credentials('SPRING_DATASOURCE_USERNAME')
        SPRING_DATASOURCE_PASSWORD=credentials('SPRING_DATASOURCE_PASSWORD')
    }
    stages {
        stage('Build') {
            agent {
                docker {
                    image 'maven:3.6.3-jdk-11-slim'
                }
            }
            steps {
                sh '(cd ./StockArc/src/main/resources/; cp application.properties.example application.properties)'
                sh '(cd ./StockArc/; mvn -Dmaven.test.skip=true clean package)'
                stash name: "app", includes: "**"
            }
        }
        stage('QualityTest') {
            agent {
                docker {
                    image 'maven:3.6.3-jdk-11-slim'
                }
            }
            steps {
                unstash "app"
                sh '(cd ./StockArc/; mvn clean test)'
                sh '(cd ./StockArc/; mvn sonar:sonar -Dsonar.projectKey=julienlinder_StockArc -Dsonar.organization=julienlinder -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=25ab78fb883118bfeb0cdb2d53c65bcef9c0c4b7)'
            }
        }
        stage('IntegrationTest') {
            agent {
                docker {
                    image 'lucienmoor/katalon-for-jenkins:latest'
                    args '-p 8888:8080'
                }
            }
            steps {
                unstash "app"
                /*sh 'java -jar ./StockArc/target/StockArc-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &'
                sh 'sleep 30'
                sh 'chmod +x ./runTest.sh'
                sh './runTest.sh'*/

                cleanWs()

            }

        }
    }
    post {
        always {
            echo 'always clean up'
            deleteDir()
        }
    }
}
