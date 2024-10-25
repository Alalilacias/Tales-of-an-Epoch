# Conventional Commits Cheat Sheet

## Overview
Conventional Commits is a convention for writing standardized commit messages. It helps with automated versioning, generating changelogs, and communicating the nature of changes in a project.

### Basic Structure
A commit message consists of a **header**, **optional body**, and **optional footer**:

```
<type>(<scope>): <subject>

<body>

<footer>
```

### Commit Message Parts

1. **Header**:
    - **type**: The type of change (mandatory).
    - **scope**: The part of the codebase affected (optional).
    - **subject**: A short description of the change (mandatory).

   **Example**:
   ```
   feat(auth): add JWT authentication
   ```

2. **Body**:
    - Provides more detailed information about the change (optional).
    - Use this to explain the reasoning behind the change or its effects.

   **Example**:
   ```
   This commit introduces JWT authentication to enhance security.
   Users can now log in with tokens instead of sessions.
   ```

3. **Footer**:
    - Can contain information about breaking changes or issue references (optional).
    - Breaking changes should start with `BREAKING CHANGE:`.

   **Example**:
   ```
   BREAKING CHANGE: The login API endpoint has changed.
   Fixes #42
   ```

### Common Commit Types

| Type      | Full Name                      | Explanation                                                                |
|-----------|--------------------------------|----------------------------------------------------------------------------|
| feat      | Features                       | A new feature                                                             |
| fix       | Bug Fixes                     | A bug fix                                                                 |
| docs      | Documentation                  | Documentation only changes                                                |
| style     | Styles                         | Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc.) |
| refactor  | Code Refactoring               | A code change that neither fixes a bug nor adds a feature                 |
| perf      | Performance Improvements       | A code change that improves performance                                     |
| test      | Tests                          | Adding missing tests or correcting existing tests                          |
| build     | Builds                         | Changes that affect the build system or external dependencies (example scopes: gulp, broccoli, npm) |
| ci        | Continuous Integrations        | Changes to our CI configuration files and scripts (example scopes: Travis, Circle, BrowserStack, SauceLabs) |
| chore     | Chores                         | Other changes that don't modify src or test files                         |
| revert    | Reverts                        | Reverts a previous commit                                                 |

### Common Scopes

| Scope        | Explanation                                                   |
|--------------|---------------------------------------------------------------|
| auth         | Authentication-related changes                                 |
| api          | Changes related to the API                                     |
| ui           | User interface changes                                         |
| db           | Database-related changes                                       |
| build        | Changes to the build system                                   |
| tests        | Changes to test-related code                                   |
| styles       | Changes to styles or CSS-related code                          |
| config       | Changes to configuration files                                 |
| docs         | Documentation changes                                          |
| example      | Example code or demo changes                                   |

### Common Footer Keywords

| Footer Keyword       | Explanation                                                   |
|----------------------|---------------------------------------------------------------|
| BREAKING CHANGE      | Indicates that this commit introduces breaking changes        |
| Fixes #<issue_number>| References an issue that is fixed by this commit             |
| Closes #<issue_number>| Indicates that the referenced issue is closed by this commit |