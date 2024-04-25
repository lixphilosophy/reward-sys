# Rewards Program
![Coverage](.github/badges/jacoco.svg)
![Branches](.github/badges/branches.svg)

## Live Demo
TBD

## Project assumption
#### Business Requirements:
Reward Calculation Logic: A customer earns rewards based on their spending per transaction:
- Earn 1 point for every dollar spent over $50 up to $100.
- Earn an additional 2 points for every dollar spent over $100.
- Transactions under $50 earn no points.

#### Technical Requirements:
- Input data will be received in JSON format containing details such as customer ID, transaction amount, and date.
- Api should support the following:
  - Add a transaction for a specific customer.
  - Add multiple transactions for multiple customers.
  - Get the reward points earned for each customer per month and total. (support filtering: by customer name and time period)
- The primary requirement is to develop a backend service, lightweight frontend (ReactJS) support show the functionality of the backend.
- No swagger documentation required.

#### Testing Assumptions:
- Comprehensive unit tests will be created using JUnit and Mockito to simulate various scenarios and validate the reward calculation logic.
- Jacoco will be used to measure the code coverage of the unit tests.
- Integration test should be done by postman collection, relative input should be provided.

#### Deployment Assumptions:
- simple fast deployment for better presentation (AWS Elastic BeanTalk & Amplify).

## Tech Stack

Spring Boot + H2 + JPA

| Name        | Version |
|-------------|---------|
| Java        | 17      |
| Spring boot | 3.2.5   |
| Spring JPA  | 3.2.5   |
| lombok      | 1.18.20 |
| h2          | 2.2.224 |
| jacoco      | 0.8.7   |

## Get Started

1. Clone the repository
2. Run the application in the IDE
3. In-memory initially come with two rows of data in each database
4. There are three endpoints available in the application:
   - Get the reward points earned for each customer per month and total:
      - calling the endpoint `localhost:8080/reward/api/v1/pointSummary/getAll`
   - Add a single transaction for a customer:
      - calling the endpoint `localhost:8080/transaction/api/v1/add`
   - Add multiple transactions for multiple customer:
      - calling the endpoint `localhost:8080/transaction/api/v1/addAll`
5. There is a postman collection available in the repository to test the endpoints
   - File location: [docs/reward-sys.postman_collection](docs/reward-sys.postman_collection.json)
   - Instruction: [Import data into Postman](https://learning.postman.com/docs/getting-started/importing-and-exporting/importing-data/)

## Error handling
TBD