# Database Setup Instructions

## Prerequisites
You need to have access to an Azure MySQL database. The connection string shows:
- Host: `sabordelsol.mysql.database.azure.com`
- Port: `3306`
- Database: `sabordelsol`

## Setting up Environment Variables

1. **Edit the `.env` file** in the backend directory with your actual database credentials:

```bash
DB_NAME=sabordelsol
DB_USER=your_actual_username
DB_PASSWORD=your_actual_password
```

2. **Replace the placeholder values:**
   - `your_actual_username` - Your Azure MySQL username
   - `your_actual_password` - Your Azure MySQL password
   - Verify `sabordelsol` is the correct database name

## What was fixed:

1. **Added dotenv-java dependency** to `pom.xml` for loading environment variables from `.env` file
2. **Modified SabordelsolApplication.java** to load environment variables on startup
3. **Created `.env` file** with template values
4. **Added `.env` to `.gitignore`** to prevent committing sensitive data

## Testing the Connection

After updating the `.env` file with correct credentials, run:

```bash
./mvnw spring-boot:run
```

The application should now successfully connect to the database.

## Troubleshooting

If you still get connection errors:
1. Verify your Azure MySQL credentials are correct
2. Check if your IP address is whitelisted in Azure MySQL firewall rules
3. Ensure the database name exists on the server
4. Verify SSL settings are compatible with your Azure MySQL configuration

## Security Notes

- Never commit the `.env` file to version control
- The `.env` file has been added to `.gitignore`
- Consider using Azure Key Vault or other secure credential storage for production
