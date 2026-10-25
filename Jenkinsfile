pipeline {
    agent any

    environment {
        // Docker Hub image name — update to your Docker Hub username
        IMAGE_NAME    = 'greatwarrior44/java-calculator'
        IMAGE_TAG     = "${env.BUILD_NUMBER}"
        REGISTRY      = 'docker.io'
        EMAIL = 'chaityanirmal.shah@iiitb.ac.in'
    }

    triggers {
        // Poll SCM every 2 minutes as fallback (webhook not needed for local Jenkins)
        pollSCM('H/2 * * * *')
    }

    stages {

        stage('Checkout') {
            steps {
                echo '── Checking out source code ──'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo '── Building JAR with Maven ──'
                bat 'mvn clean package -DskipTests -q'
            }
            post {
                success { echo 'Build stage succeeded.' }
                failure { echo 'Build stage FAILED.' }
            }
        }

        stage('Test') {
            steps {
                echo '── Running JUnit 5 tests ──'
                bat 'mvn test'
            }
            post {
                always {
                    // Publish JUnit XML reports in Jenkins
                    junit 'target/surefire-reports/*.xml'
                }
                success { echo 'All tests passed.' }
                failure { echo 'Tests FAILED.' }
            }
        }

        stage('Docker Build') {
            steps {
                echo "── Building Docker image: ${IMAGE_NAME}:${IMAGE_TAG} ──"
                bat "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} -t ${IMAGE_NAME}:latest ."
            }
        }

        stage('Docker Push') {
            steps {
                echo '── Pushing image to Docker Hub ──'
                withCredentials([usernamePassword(
                    credentialsId: 'docker-credentials',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    bat """
                        echo %DOCKER_PASS%| docker login -u %DOCKER_USER% --password-stdin
                        docker push ${IMAGE_NAME}:${IMAGE_TAG}
                        docker push ${IMAGE_NAME}:latest
                        docker logout
                    """
                }
            }
        }

        stage('Deploy') {
            steps {
                echo '── Deploying with Docker Compose ──'
                bat '''
                    docker compose down --remove-orphans
                    docker compose up -d
                '''
            }
        }
    }

    post {
        success {
            mail(
                to:      "${EMAIL}",
                subject: "Jenkins Build #${env.BUILD_NUMBER} - SUCCESS",
                body:    """\
Build #${env.BUILD_NUMBER} of ${env.JOB_NAME} succeeded.

Branch  : ${env.GIT_BRANCH}
Commit  : ${env.GIT_COMMIT}
Duration: ${currentBuild.durationString}

View build: ${env.BUILD_URL}
"""
            )
        }
        failure {
            mail(
                to:      "${EMAIL}",
                subject: "Jenkins Build #${env.BUILD_NUMBER} - FAILURE",
                body:    """\
Build #${env.BUILD_NUMBER} of ${env.JOB_NAME} FAILED.

Branch  : ${env.GIT_BRANCH}
Commit  : ${env.GIT_COMMIT}
Duration: ${currentBuild.durationString}

View build: ${env.BUILD_URL}
"""
            )
        }
        always {
            echo 'Pipeline finished. Cleaning up local Docker images...'
            bat "docker rmi ${IMAGE_NAME}:${IMAGE_TAG} & exit /b 0"
        }
    }
}
