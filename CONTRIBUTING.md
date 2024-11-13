# CONTRIBUTING.md

Handling branching in Git for a large project, especially in the context of a game with both backend and frontend components, is crucial for maintaining organization and collaboration. Here’s our branching and workflow strategy. Please follow these guidelines.

### 1. **Branching Strategy**
- **Main Branch (`main`)**: Contains stable, production-ready code and reflects completed, tested features and bug fixes. Only authorized contributors should merge into this branch.
- **Development Branch (`develop`)**: Serves as the integration branch for ongoing development. Completed features should be merged here for testing before being moved to `main`.
- **Feature Branches**: For each new feature or piece of functionality, create a separate branch from `develop`. Use a naming convention like `feature/feature-name`.
- **Bugfix Branches**: Similar to feature branches, but for bug fixes. Use a naming convention like `bugfix/bug-description`.
- **Hotfix Branches**: For urgent fixes needed directly in `main`. Name them like `hotfix/fix-description`.

### 2. **Workflow Example**

1. **Pull the Latest Develop Branch**:
   ```bash
   git checkout develop
   git pull origin develop
   ```

2. **Create a Feature Branch**:
   ```bash
   git checkout -b feature/login-system
   ```

3. **Implement Your Feature**: Make changes, then commit them with clear, descriptive messages.
   ```bash
   git add .
   git commit -m "feat(login): implement user login system"
   ```

4. **Merge Feature into Develop**:
   ```bash
   git checkout develop
   git merge feature/login-system
   ```

5. **Test in Develop**: Ensure everything works as expected in the `develop` branch.

### 3. **Commit Strategy**

To keep commit history clean and meaningful, we follow a **Commit Strategy** using clear, conventional commit messages that describe the purpose of each change. This helps maintainers and other developers understand what each change accomplishes at a glance.

- **Conventional Commits**: Use a structured message format to indicate the type, scope, and purpose of each commit. Example format:
  ```
  <type>(<scope>): <subject>
  ```
   - **type**: Describes the kind of change (e.g., `feat`, `fix`, `docs`, `refactor`, etc.).
   - **scope**: Briefly describes the area of the code affected (e.g., `login`, `database`, `UI`).
   - **subject**: A concise description of the change.

- **Examples**:
  ```bash
  git commit -m "feat(auth): add JWT authentication"
  git commit -m "fix(profile): correct avatar upload bug"
  ```

- **Common Commit Types**:
   - `feat`: A new feature
   - `fix`: A bug fix
   - `docs`: Documentation-only changes
   - `refactor`: Code changes that neither fix a bug nor add a feature
   - `test`: Adding or updating tests
   - `style`: Code style changes (e.g., formatting, indentation)

### 4. **Pull Requests (PR)**

- **Creating a PR**: Once you push your feature branch to GitHub, create a PR to `develop`. This allows code reviews, testing, and discussion.
- **Merging PRs**: Once approved, merge the PR into `develop`. Major releases to `main` should be done with a PR from `develop` to `main` by senior contributors.

### 5. **Branch Cleanup**

After merging a feature or bugfix branch, delete it to keep the branch list clean:
```bash
git branch -d feature/login-system
```

### 6. **Regular Merges and Updates**

- Regularly merge `develop` into your feature branches to stay updated with recent changes and minimize conflicts.
- Consider **rebasing** on `develop` to keep your branch history clean, but consult with senior contributors if needed.

For quick reference, additional cheat sheets on commit message conventions, branch naming, and Git commands are available in the `cheatsheets` folder.