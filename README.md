# Rewards Program
![Coverage](.github/badges/jacoco.svg)
![Branches](.github/badges/branches.svg)

## Live Demo
TBD

## Project assumption
TBD

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